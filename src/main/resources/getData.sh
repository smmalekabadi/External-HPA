curl -s -X GET 'http://5.61.28.210/zabbix/api_jsonrpc.php' \
--header 'Content-Type: application/json-rpc' \
--data-raw '{
   "jsonrpc":"2.0",
   "method":"item.get",
   "params":{
      "output":"extend",
      "hostids":"10347",
      "search":{
         "key_":"nginx.request"
      },
      "sortfield":"name"
   },
   "auth":"7c01463c533fd1df55087a7c8294ea16",
   "id":1
}'| jq '.result[].lastvalue'