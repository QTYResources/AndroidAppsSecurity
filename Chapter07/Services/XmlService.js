// load http module
var http = require('http')

http.createServer(function(request, response) {
    console.log("url: " + request.url + ", method: " + request.method)
    if (request.url == '/apress/members') {
        if (request.method == 'GET') {
            var content = '\
<apress>\n\
    <users>\n\
        <user name="Sheran" email="sheranapress@gmail.com" />\n\
        <user name="Kevin" email="kevin@example.com" />\n\
        <user name="Scott" email="scottm@example.com" />\n\
    </users>\n\
</apress>\n'
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
