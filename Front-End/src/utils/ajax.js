
export function postRequest(url, json, callback)
{
    let opts = {
        method: "POST",
        body: JSON.stringify(json),
        headers: {
            'Content-Type': 'application/json'
        },
        credential: "include"
    }

    // console.log("opts");
    // console.log(opts);

    fetch(url, opts)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            // console.log("Ajax function:\n");
            // console.log(data);
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        })
}

export function postRequest_noRet(url, json) {
    let opts = {
        method: "POST",
        body: JSON.stringify(json),
        headers: {
            'Content-Type': 'application/json'
        },
        credential: "include"
    }
    // console.log("opts");
    // console.log(opts);
    fetch(url, opts);
}

