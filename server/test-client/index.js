const fetch = require('node-fetch');
const { exit } = require('process');
const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Fetch what state code (AZ, MI, FL): ", function(code) {

    rl.question("Fetch what data?\n\n1 - Population\n2 - Daily Cases\n3 - Total Cases\n\nEnter a coice: ", function(num) {

        fetch('http://localhost:4567/' + code)
            .then(res => res.json())
            .then(json => {

                switch (num) {
                    case "1":
                        console.log(code + " has a population of " + json["population"] + ".")
                        break;
                    case "2":
                        console.log(code + " has " + json["dailyCases"] + " daily cases.")
                        break;
                    case "3":
                        console.log(code + " has " + json["totalCases"] + " total cases.")
                        break;
                }

                exit(0)

            });

    })

})

