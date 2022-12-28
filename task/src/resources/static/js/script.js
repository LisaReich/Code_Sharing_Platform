//const codeSnippetElement = document.getElementById("code_snippet");
//const submitCodeButtonElement = document.getElementById("send_snippet");
//
//const createCodeSnippet = () => {
//
//    const codeBody = {"code": codeSnippetElement.value};
//
//    fetch("/api/code/new", {
//        method: "POST",
//        headers: {
//            "Content-Type": "application/json"
//        },
//        body: JSON.stringify(codeBody)
//    })
//    .then(response => console.log("Code snippet has been created!"));
//};
//
//submitCodeButtonElement.addEventListener("click", createCodeSnippet);

function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
      alert("Success!");
    }
}
