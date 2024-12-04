"use strict"

async function login(e) {

    await fetch('/login/process-login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: user
    }).then(() => {
        console.log(`success!`);
    })

    const form = document.querySelector("#regForm");

    const user = await JSON.stringify({
        username: form.username.value.trim(),
        password: form.password.value,
        roles: ['USER']
    })
}