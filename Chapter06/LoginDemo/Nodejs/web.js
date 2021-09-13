var http = require('http');
var querystring = require('querystring');
var server = http.createServer().listen(8124);
server.on('request', function (request, response) {
    console.log(request.url)
    if (request.url == '/logindemo') {
        if (request.method == 'POST') {
            var body = '';
            // append data chunk to body
            request.on('data', function (data) {
                body += data;
            });
            // data transmitted
            request.on('end', function () {
                var post = querystring.parse(body);
                console.log("username: " + post["username"] + ", password: " + post["password"]);
                response.writeHead(200, { 'Content-Type': 'text/plain' });
                if (post["username"] == 'sheran' && post["password"] == 's3kr3tc0dez') {
                    response.end('Login success!!')
                } else {
                    response.end('Login failed!!\n');
                }
            });
        } else if (request.method == 'GET') {
            response.writeHead(200, { 'Content-Type': 'text/plain' });
            response.end('Hello World\n');
        }
    }
});
console.log('server listening on 8214');