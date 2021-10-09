// load http module
var http = require('http')

http.createServer(function(request, response) {
    console.log("url: " + request.url + ", method: " + request.method)
    if (request.url == '/apress/members') {
        if (request.method == 'GET') {
            var content = '\
{\n\
    users:{\n\
        user:[\n\
            {\n\
                name:"Sheran",\n\
                email:"sheranapress@gmail.com"\n\
            },\n\
            {\n\
                name:"Kevin",\n\
                email:"kevin@example.com"\n\
            },\n\
            {\n\
                name:"Scott",\n\
                email:"scott@example.com"\n\
            }\n\
        ]\n\
    }\n\
}'
            response.writeHead(200, { 'Content-Type': 'text/plain' });
            response.end(content);
        } else {
            response.writeHead(200, { 'Content-Type': 'text/plain' });
            response.end('Hello World\n');
        }
    } else {
        response.writeHead(200, { 'Content-Type': 'text/plain' });
        response.end('Hello World\n');
    }
}).listen(80)
console.log('server listening on 80');
