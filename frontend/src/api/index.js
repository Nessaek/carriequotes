export function fetchQuote() {
    return fetch("http://localhost:8080/quote").then(function (response) {
        if (!response.ok) {
            throw Error(response.statusText);
        }
        // Read the response as json.
        return response;
    })
        .then(function (response2) {
            // Do stuff with the JSON
            return response2.text();
        })
        .then(function (response3) {
            return response3
        })
        .catch(function (error) {
            console.log('Looks like there was a problem: \n', error);
        })



}