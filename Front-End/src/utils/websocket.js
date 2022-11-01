
export let websocket = null;

export function openWebSocket (uid) {
    websocket = new WebSocket("ws://localhost:8080/websocket/order/" + uid);

    websocket.onopen = function () {
        console.log(uid + " connected!")
    }

    websocket.onclose = function () {
        console.log(uid + " disconnected")
    }

    websocket.onerror = function () {
        console.log(uid + " connection error")
    }
}
