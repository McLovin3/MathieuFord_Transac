const URL = "http://localhost:8080/";

export async function postBook(book) {
    return await fetch(URL + "documents",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(book)
        });
}

export async function postCD(CD) {
    return await fetch(URL + "documents",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(CD)
        });
}

export async function postClient(client) {
    return await fetch(URL + "clients",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(client)
        });
}

export async function postDVD(DVD) {
    return await fetch(URL + "documents",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(DVD)
        });
}

export async function fetchAttendant(attendantId) {
    return await fetch(URL + "attendants/" + attendantId);
}

export async function fetchClient(clientId) {
    return await fetch(URL + "clients/" + clientId);

}

export async function putBorrow(borrow) {
    return await fetch(URL + "borrows",
        {
            method: "PUT",
            headers:
            {
                "Content-Type":
                    "application/json"
            },
            body:
                JSON.stringify(borrow)
        });
}

export async function fetchBorrows(userId) {
    return await fetch(URL + "borrows/" + userId);
}

export async function postBorrow(documentId, clientId) {
    return await fetch(URL + "borrows",
        {
            method: "POST",
            headers:
            {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ "documentId": documentId, "clientId": clientId })
        }
    );
}

export async function fetchDocuments() {
    return await fetch(URL + "documents");
}

export async function fetchClients() {
    return await fetch(URL + "clients");
}

export async function fetchAttendants() {
    return await fetch(URL + "attendants");
}

