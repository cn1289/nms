<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- template from Christophe Coenraets -->
<!-- 
   Copyright 2012 IBM

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 -->
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>NMS</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <!-- Place this tag in your head or just before your close body tag -->
    <![endif]-->

    <!-- Le styles -->
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <link href="css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />
    <link href="lib/prettify/prettify.sunburst.css" rel="stylesheet" type="text/css" />
    
    <!-- Le fav and touch icons -->
    <!-- TODO create some icons 
    <link rel="shortcut icon" href="/img/favicon.ico">
    <link rel="apple-touch-icon" href="/img/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/img/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/img/apple-touch-icon-114x114.png">
    -->
</head>

<body>
<input id="type" type="hidden" value="1" />
<div class="header"></div>

<div id="toolbox"></div>

<div class="container">
<div id="content"></div>
</div> <!-- /container -->
<div id="switchInfo" style="display:none;">
    switchInfo
</div>
<div id="networkInfo" style="display:none;">
    SSID:<input id="ssid"><br>
    <input id="networkId" type="hidden">
    Encryption:<input id="encryption"><br>
    EncryptionKey:<input id="encryptionKey" type="password"><br>
    <input id="btnChangeNetworkInfo" type="button" onclick="changeNetworkInfo();" value="提交" />
</div>
<div id="hostInfo" style="display:none;">
	Host name:<input id="hostName" name="hostName" readonly="readonly">
    Vendor:<span id="vendor"></span><br>
    Mac address<input id="src-mac" name="src-mac"  width="100px" readonly="readonly" /><br>
    nw-proto<input id="nw-proto" name="nw-proto" value="TCP" width="100px" readonly="readonly" ><br>
    tp-src<input id="tp-src" name="tp-src" value="8080"  width="100px" readonly="readonly" /><br>
    action:
    <select id="action" name="action" disabled="disabled">
    <option value="ALLOW">ALLOW</option>
    <option value="DENY">DENY</option>
    </select>
    <input id="rename" type="button" onclick="btnRenameClick();" value="改名" />
    <input id="forbiddenPort" type="button" onclick="changePortStatus('DENY');" value="禁用8080端口" />
    <input id="startPort" type="button" onclick="changePortStatus('ALLOW');" value="启用8080端口" />
</div>

<script src="lib/jquery.min.js"></script>
<script src="lib/d3.v2.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="lib/backbone-min.js"></script>

<script src="lib/bootstrap-dropdown.js"></script>
<script src="lib/bootstrap-alert.js"></script>

<script src="js/utils.js"></script>
<script src="js/models/hostmodel.js"></script>
<script src="js/models/apmodel.js"></script>
<script src="js/models/firewallmodel.js"></script>
<script src="js/models/topologymodel.js"></script>
<script src="js/models/statusmodel.js"></script>
<script src="js/models/switchmodel.js"></script>
<script src="js/models/portmodel.js"></script>
<script src="js/models/flowmodel.js"></script>
<script src="js/models/radiomodel.js"></script>
<script src="js/models/networkmodel.js"></script>
<script src="js/views/header.js"></script>
<script src="js/views/home.js"></script>
<script src="js/views/radio.js"></script>
<script src="js/views/network.js"></script>
<script src="js/views/status.js"></script>
<script src="js/views/host.js"></script>
<script src="js/views/switch.js"></script>
<script src="js/views/ap.js"></script>
<script src="js/views/topology.js"></script>
<script src="js/views/port.js"></script>
<script src="js/views/flow.js"></script>

<script src="js/main.js"></script>

</body>
</html>
