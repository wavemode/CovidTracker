const DATA_ENDPOINT = "http://35.231.3.11"

let readyFlag = false
// document.addEventListener('deviceready', onContentLoaded, false);
document.addEventListener('DOMContentLoaded', onContentLoaded, false);

/*
    Retrieve the user's current location. Promise rejection happens if
    no success within timeout milliseconds (default 5000).
*/
function getUserLocation(timeout) {
    timeout = timeout || 5000
    return new Promise((resolve, reject) => {
        navigator.geolocation.getCurrentPosition(resolve, reject, {timeout: timeout})
    })
}

/*
    Retrieve the county closest to a given geographic point.
*/
function locationSearchRequest(latitude, longitude) {
    return new Promise((resolve, reject) => {
        fetch(`${DATA_ENDPOINT}/location?lat=${latitude}&long=${longitude}`).then((res) => {
            res.json().then(resolve).catch(reject)
        }).catch(reject)
    })
}

/*
    Search for counties matching a given name. Will be limited to 10 results.
*/
function textSearchRequest(search) {
    return new Promise((resolve, reject) => {
        fetch(`${DATA_ENDPOINT}/search?q=${search}`).then((res) => {
            res.json().then(resolve).catch(reject)
        }).catch(reject)
    })
}

/*
    Get the user's location and display the county at that location.
*/
async function initCounty() {
    try {
        let location = await getUserLocation()
        let nearestCounty = await locationSearchRequest(location.coords.latitude, location.coords.longitude)
        vueApp.userCounty = nearestCounty

        initMap(location.coords.latitude, location.coords.longitude)

    } catch (e) {
        console.log(e)
        setTimeout(initCounty, 1000)
    }
}

/*
    Load contacts from localStorage.
*/
function loadContacts() {
    let contacts = window.localStorage.getItem('contacts')
    if (contacts)
        vueApp.contact = JSON.parse(contacts)
}


/*
    Save contacts to localStorage.
*/
function saveContacts() {
    window.localStorage.setItem('contacts', JSON.stringify(vueApp.contacts))
}

/*
    Send an SMS to the given number
*/
function sendSMS(number, message) {

    return new Promise(async (resolve, reject) => {

        // requestSMSPermission().catch((err) => {
        //     alert('Coult not acquire SMS permission: ' + err)
        // }).then(() => {

            number = '' + number
            message = '' + message
            console.log("number=" + number + ", message= " + message);

            //CONFIGURATION
            var options = {
                replaceLineBreaks: false, // true to replace \n by a new line, false by default
                android: {
                    intent: ''  // send SMS with the native android SMS messaging
                    //intent: '' // send SMS without opening any other app, require : android.permission.SEND_SMS and android.permission.READ_PHONE_STATE
                }
            };

            sms.send(number, message, options, resolve, reject);

        // })
    })
}

/**
 * Check permission to send SMS
 */
function checkSMSPermission() {
    return new Promise((resolve, reject) => {
        sms.hasPermission(resolve, reject);
    })
}

/**
 * Request permission to send SMS
 */
function requestSMSPermission() {
    return new Promise((resolve, reject) => {
        let success = (hasPermission) => { 
            if (!hasPermission) {
                alert('we dont have permission')
                sms.requestPermission(resolve, reject)
            }
        };
        alert('checking if has permission')
        sms.hasPermission(success, reject);
    })

}

function onContentLoaded() {
    // if (!readyFlag) {
    //     readyFlag = true
    //     return
    // }

    window.vueApp = new Vue({
        el: '#app',
        data: {
            title: 'Home',
            userCounty: null,
            dailyCases: 1014,
            deaths: 483,
            totalCases: 192321,
            recovered: 45673,
            locationSearchCounties: [],
            activePage: 'home',
            warningColor: 'red',
            contacts: [],
            contactFormName: '',
            contactFormPhone: '',
            contactFormDate: '',
            contactFormTime: '',
            editContactIndex: -1,
            tableClass: 'mdl-data-table--selectable',
        },
        methods: {
            commaNumber: function(num) {

                // numebrs that are approximately zero should be reported as such
                if (num < 1 && num > -1) {
                    return '~0'
                }

                num = '' + num
                var result = ''
                var count = 0;
                for (var i = 0; i < num.length; ++i) {
                    ++count;
                    result = num.charAt(num.length - i - 1) + result
                    if (count % 3 === 0 && i < num.length - 1)
                        result = ',' + result
                }
                return result
            },
            navigate: function(page, event) {
                if (event) {
                    this.title = event.target.innerHTML.trim()
                }
                this.activePage = page
                var elem = document.querySelector('.mdl-layout__obfuscator')
                if (elem.className.indexOf('is-visible') !== -1)
                    elem.click()
            },
            warningText() {
                if (!this.userCounty) return ''

                let dailyPer100k = this.userCounty.dailyCases / (this.userCounty.population / 100000)

                if (dailyPer100k > 25) {
                    this.warningColor = 'red'
                    return '<strong>Severe:</strong> This area has unchecked community spread.'
                } else if (dailyPer100k > 10) {
                    this.warningColor = 'orange'
                    return '<strong>Danger:</strong> This area has escalating community spread.'
                } else if (dailyPer100k > 1) {
                    this.warningColor = 'yellow'
                    return '<strong>Moderate:</strong> This area has potential community spread.'
                } else {
                    this.warningColor = 'green'
                    return '<strong>Mild:</strong> This area is close to containment of Covid-19.'
                }
            },
            locationSearchInput(event) {
                let search = event.target.value.trim()
                if (search === '') {
                    this.locationSearchCounties = []
                } else {
                    textSearchRequest(search).then((counties) => {
                        this.locationSearchCounties = counties
                    }).catch(console.error)
                }
            },
            loadStats(event) {
                console.log(event.target)
                let fips = event.target.parentElement.querySelector('.search-result-fips').innerHTML.trim()

                let county = this.locationSearchCounties.find(county => county.fipsCode == fips)

                this.userCounty = county
                googleMap.setCenter({ lat: county.latitude, lng: county.longitude })
                this.navigate('home')
            },
            submitContact() {
                let contact = {
                    name: this.contactFormName,
                    phone: this.contactFormPhone,
                    date: this.contactFormDate,
                    time: this.contactFormTime
                }
                if (this.editContactIndex === -1)
                    this.contacts.push(contact)
                else
                    this.contacts[this.editContactIndex] = contact
                this.editContactIndex = -1
                this.contactFormName = ''
                this.contactFormPhone = ''
                this.contactFormDate = ''
                this.contactFormTime = ''
                this.navigate('contacts')

                saveContacts()
            },
            newContact() {
                let date = new Date()
                this.contactFormDate = date.toISOString().split('T')[0]
                var h = date.getHours();
                var m = date.getMinutes();
                var x = h >= 12 ? 'p.m.' : 'a.m.';
                h = h % 12;
                h = h ? h : 12;
                m = m < 10 ? '0'+m: m;
                this.contactFormTime = h + ':' + m + ' ' + x;
                this.navigate('new-contact')
            },
            deleteContact() {
                let contacts = this.contacts
                contacts.splice(this.editContactIndex, 1)
                this.contacts = contacts
                this.navigate('contacts')

                saveContacts()
            },
            editContact(index) {
                this.editContactIndex = index
                let contact = this.contacts[index]
                this.contactFormName = contact.name
                this.contactFormPhone = contact.phone
                this.contactFormDate = contact.date
                this.contactFormTime = contact.time
                this.navigate('new-contact')
            }
        }
    })

    loadContacts()

    initCounty()

}

function initMap(lat, lng, limit) {
    limit = limit || 100
    if (limit < 1) return

    var elem = document.getElementById('map')
    if (!elem) {
        setTimeout(() => initMap(lat, lng, limit - 1), 50)
        return
    }

    window.googleMap = new google.maps.Map(elem, {
        center: {lat: lat, lng: lng},
        zoom: 8,
        gestureHandling: "none",
        disableDefaultUI: true
    });
}
