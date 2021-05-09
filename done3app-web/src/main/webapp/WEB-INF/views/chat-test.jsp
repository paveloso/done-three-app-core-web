<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Done 3 App Login</title>
</head>
<body>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sockjs.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sockjs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/stomp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/stomp.min.js"></script>
<script>
    var stompClient = null;
    var socket = null;

    onMessageReceived = function(msg) {
        console.log("msg received", msg);
    };

    function onConnected() {
        console.log("connected");
        stompClient.subscribe("/user/" + ${sessionScope.user.id} + "/queue/messages", function(message) {
            var msg = JSON.parse(message.body);
            console.log(msg);
        });
    }

    <%--function onConnected() {--%>
    <%--    console.log("connected");--%>
    <%--    stompClient.subscribe("/user/" + ${sessionScope.user.id} + "/queue/messages", onMessageReceived);--%>
    <%--}--%>

    const onError = (error) => {
        console.log(error)
    };

    function connect() {
        // var SockJS = new SockJS('http://localhost:8080/web/ws');
        var sockjs = new SockJS('http://192.168.1.222:8080/web/ws');
        // socket = new WebSocket('wss://localhost:8080/web/ws');
        stompClient = Stomp.over(sockjs);
        // var url = "ws://localhost:8080/ws";
        // stompClient = Stomp.client(url);
        stompClient.connect({}, onConnected, onError);
    }

    function sendMessage(msg) {
        msg = "hello from chat";
        if (msg.trim() !== "") {
            const message = {
                chatId: "0",
                senderId: "2",
                recipientId: "1",
                senderName: "admin",
                recipientName: "check",
                content: msg,
                timestamp: new Date()
            };

            stompClient.send("/app/chat", {}, JSON.stringify(message));
            // socket.send(JSON.stringify(message));
        }
    }
</script>
<h3>Welcome to the Done 3 App Chat</h3>

<input type="button" value="connect" onclick="connect()">
<input type="button" value="send msg" onclick="sendMessage('ololo')">

</body>
</html>