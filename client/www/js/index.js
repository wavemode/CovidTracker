// document.addEventListener('deviceready', onDeviceReady, false);
document.addEventListener('DOMContentLoaded', onContentLoaded, false);

var counties = [
    { name: 'Cook County', state: 'IL' },
    { name: 'Orange County', state: 'CA' },
    { name: 'Oakland County', state: 'MI' },
    { name: 'Wayne County', state: 'MI' },
    { name: 'Macomb County', state: 'MI' },
    { name: 'Washtenaw County', state: 'MI' },
]

function onContentLoaded() {
    window.vueApp = new Vue({
        el: '#app',
        data: {
            title: 'Home',
            county: {
                name: 'Oakland County',
                state: 'MI',
                statusClass: 'red',
            },
            dailyCases: 1014,
            deaths: 483,
            totalCases: 192321,
            recovered: 45673,
            locationSearchCounties: [],
            activePage: 'home',
        },
        methods: {
            commaNumber: function(num) {
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
            warningText(statusClass) {
                if (statusClass === 'red') {
                    return '<strong>Severe:</strong> This area is a Covid-19 hotspot. Exercise caution.'
                } else if (statusClass === 'yellow') {
                    return '<strong>Moderate:</strong> This area is recovering, but still has significant infections.'
                } else if (statusClass === 'green') {
                    return '<strong>Mild:</strong> This area is mostly free of Covid-19.'
                }
            },
            locationSearchInput(event) {
                let search = event.target.value.trim().toLowerCase()
                if (search === '')
                    this.locationSearchCounties = []
                else
                    this.locationSearchCounties = counties.filter(county =>
                        county.name.toLowerCase().indexOf(search) !== -1)
            },
            loadStats(event) {
                console.log(event.target)
                let name = event.target.parentElement.querySelector('.search-result-name').innerHTML.trim()
                let county = counties.find(county => county.name === name)
                county.statusClass = Math.random() > 0.66 ? 'green' : Math.random() > 0.33 ? 'yellow' : 'red';
                
                this.totalCases = Math.floor(Math.random() * 1000000)
                this.deaths = Math.floor(Math.random() * 10000)
                this.recovered = Math.floor(Math.random() * 100000)
                this.dailyCases = Math.floor(Math.random() * 1000)
                
                this.county = county
                this.navigate('home')
            }
        }
    })

    setTimeout(() => {
        app.county = {
            name: 'Cook County',
            state: 'IL'
        }
    }, 10000)
}

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 8
      });      
}