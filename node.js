// Download the helper library from https://www.twilio.com/docs/node/install
// Your Account Sid and Auth Token from twilio.com/console
// and set the environment variables. See http://twil.io/secure
const accountSid = process.env.TWILIO_ACCOUNT_SID;
const authToken = process.env.TWILIO_AUTH_TOKEN;
const client = require('twilio')(accountSid, authToken);
var accountSid = 'AC4de4f5c9fb5b5a5432b139c2a12fdcff'; // Your Account SID from www.twilio.com/console
var authToken = '4804083e872eb08899fcc0074bd101c5';   // Your Auth Token from www.twilio.com/console

var twilio = require('twilio');
var client = new twilio(accountSid, authToken);

client.messages.create({
    body: 'Hello from Node',
    to: '+13137043384',  // Text this number
    from: '++13192505787' // From a valid Twilio number
})
.then((message) => console.log(message.sid));
client.messages
  .create({
     body: 'This is the ship that made the Kessel Run in fourteen parsecs?',
     from: '++13192505787',
     to: '+13137043384'
   })
  .then(message => console.log(message.sid));
EXAMPLE JSON API RESPONSE
{
  "account_sid": "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
  "api_version": "2010-04-01",
  "body": "This is the ship that made the Kessel Run in fourteen parsecs?",
  "date_created": "Thu, 30 Jul 2015 20:12:31 +0000",
  "date_sent": "Thu, 30 Jul 2015 20:12:33 +0000",
  "date_updated": "Thu, 30 Jul 2015 20:12:33 +0000",
  "direction": "outbound-api",
  "error_code": null,
  "error_message": null,
  "from": "+15017122661",
  "messaging_service_sid": "MGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
  "num_media": "0",
  "num_segments": "1",
  "price": null,
  "price_unit": null,
  "sid": "SMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
  "status": "sent",
  "subresource_uris": {
    "media": "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/SMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Media.json"
  },
  "to": "+15558675310",
  "uri": "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/SMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.json"
}