/*
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
*/

var hackBase = "nms/"; // put a URL here to access a different REST server
var hostNames =[];
function editNetworkInfo(networkId){
	$.ajax({
		url:"/nms/getNetworkById",
		data:{id:networkId},
		dataType:'json',
		success:function(d){
			if(d){
				$("#toolbox").html($("#networkInfo").html());
				$("#networkId").val(d.id);
				$("#ssid").val(d.ssid);
				$("#encryption").val(d.encryption.encryption);
				$("#encryptionKey").val(d.encryption.encryptionKey);
				
				$("#toolbox").show();
			}
		}
	});
}
function changeNetworkInfo(){
	$.ajax({
		url:"/nms/updateNetwork",
		data:{
			networkId:$("#networkId").val(),
			ssid:$("#ssid").val(),
			encryption:$("#encryption").val(),
			encryptionKey:$("#encryptionKey").val()
		},
		dataType:'json',
		success:function(d){
			if(d===0){
				alert("更新成功");
				$("#toolbox").hide();
				$("#toolbox").html();
			}else{
				alert("更新失败");
			}
		}
	});
}
function refreshHostNames(){
	$.ajax({
		url:hackBase+"hostNames",
		dataType:'json',
		success:function(d){
			if(d){
				hostNames=d;
			}
		}
	});
}
function btnRenameClick(){
	$("#hostName").removeAttr("readonly");
	$("#hostName").bind("blur",function(){
		rename();
	});
}
function rename(){
	$.ajax({
		url:hackBase+"rename",
		data:{'macAddress':$("#src-mac").val(),
			'hostName':$("#hostName").val()},
		dataType:'json',
		success:function(d){
			if(d==1){
				alert("Successful");
				refreshHostNames();
				hl.fetch();
				$("#hostName").unbind("onblur");
			}else{
				alert("Failure");
			}
		}
	});
}
function changePortStatus(status){
	$.ajax({
		url:hackBase+"getLongAddress",
		data:{'macAddress':$("#src-mac").val()},
		dataType:'json',
		success:function(d){
			$.post("nms/addRules",{
				"src-mac":$("#src-mac").val(),
				"nw-proto":$("#nw-proto").val(),
				"tp-src":$("#tp-src").val(),
				"action":status,
				"hostName":$("#hostName").val()
				},function(data){
					alert(data.status);
					refreshFireWalls();
				},"json");
		}
	});
}
function refreshFireWalls(){
	$.ajax({
        url:"/nms/wm/firewall/rules/json",
        dataType:'json',
        success:function (data) {
            console.log("fetched  FireWall list: " + data.length);
            rules=data;
        }
    });
}

//function refreshAPList(){
//	$.ajax({
//    	type: "get",
//        url:hackBase + "apList",
//        dataType:'json',
//        success:function (data) {
//            console.log("fetched  ap list: " + data.length);
//            apList=data;
//        },
//    });
//}
function getHostName(mac){
	if(hostNames && hostNames.length >0){
		for(var i=0;i<hostNames.length;i++){
			if(hostNames[i].macAddress==mac){
				return hostNames[i].hostName;
			}
		}
	}
	return mac;
};
function getHttpPortStatus(longMac){
	console.log("rules size is "+rules.length);
	for(var i=0;i<rules.length;i++){
		var t=rules[i];
		console.log(i+"[t.dl_src:"+t.dl_src+"]"+"[longMac:"+longMac+"]");
		if(t.dl_src===longMac){
			console.log(longMac+":"+t.action);
			return t.action;
		}
	}
	return "";
};
var AppRouter = Backbone.Router.extend({

    routes:{
        "":"home",
        "topology":"topology",
        "switches":"switchList",
        "switch/:id":"switchDetails",
        "switch/:id/port/:p":"portDetails", // not clear if needed
        "hosts":"hostList",
        "host/:id":"hostDetails",
        "radio/:apId/:radioId":"radioDetails",
//        "network/:apId/:radioId/:networkId":"networkDetails",
        "aps":"apList",
        "ap/:id":"apDetails",
        // "vlans":"vlanList" // maybe one day
        // "vlan/:id":"vlanDetails"
    },

    initialize:function () {
        this.headerView = new HeaderView();
        $('.header').html(this.headerView.render().el);

        // Close the search dropdown on click anywhere in the UI
        $('body').click(function () {
            $('.dropdown').removeClass("open");
        });
    },

    home:function () {
    	$("#toolbox").hide();
        $('#content').html(new HomeView().render().el);
        $('ul[class="nav"] > li').removeClass('active');
        $('a[href="/"]').parent().addClass('active');
    },

    topology:function () {
        //console.log("switching to topology view");
    	$("#toolbox").hide();
        var topo = new Topology();
        $('#content').html(new TopologyView({model:topo, hosts:hl,aps:apl}).render().el);
        // TODO factor this code out
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="topology"]').parent().addClass('active');
    },
    
    switchDetails:function (id) {
    	$("#toolbox").hide();
        //console.log("switching [sic] to single switch view");
        var sw = swl.get(id);
        $('#content').html(new SwitchView({model:sw}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/switches"]').parent().addClass('active');
    },
    
    switchList:function () {
    	$("#toolbox").hide();
        //console.log("switching [sic] to switch list view");
        $('#content').html(new SwitchListView({model:swl}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/switches"]').parent().addClass('active');
    },

    hostDetails:function (id) {
    	$("#toolbox").hide();
        //console.log("switching to single host view");
        var h = hl.get(id);
        $('#content').html(new HostView({model:h}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/hosts"]').parent().addClass('active');
    },
    
    hostList:function () {
    	$("#toolbox").hide();
        //console.log("switching to host list view");
        $('#content').html(new HostListView({model:hl}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/hosts"]').parent().addClass('active');
    },
    apDetails:function (id) {
    	$("#toolbox").hide();
        //console.log("switching to single ap view");
        var ap = apl.get(id);
        $('#content').html(new APView({model:ap}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/aps"]').parent().addClass('active');
    },
    
    apList:function () {
    	$("#toolbox").hide();
        //console.log("switching to ap list view");
        $('#content').html(new APListView({model:apl}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/aps"]').parent().addClass('active');
    },
    radioDetails:function (apId,radioId) {
    	$("#toolbox").hide();
        //console.log("switching to single ap view");
        var radio = apl.get(apId).radios.get(radioId);
        $('#content').html(new RadioView({model:radio}).render().el);
        $('ul.nav > li').removeClass('active');
        $('li > a[href*="/aps"]').parent().addClass('active');
    }
//    ,
//    networkDetails:function (apId,radioId,networkId) {
//    	$("#toolbox").hide();
//        //console.log("switching to single ap view");
//    	var radio = apl.get(apId).radios.get(radioId).networks.get(networkId);
//        $('#content').html(new NetworkView({model:network}).render().el);
//        $('ul.nav > li').removeClass('active');
//        $('li > a[href*="/aps"]').parent().addClass('active');
//    }
});
// load global models and reuse them
var swl = new SwitchCollection();
var hl  = new HostCollection();
//取回的ap相关资源
var apl=new APCollection();
//var rules = new FireWallCollection();
var rules;
//var apList;
var updating = true;

tpl.loadTemplates(['home', 'status', 'topology', 'header', 'switch', 'switch-list', 'switch-list-item', 'host', 'host-list', 'host-list-item','radio', 'radio-list', 'radio-list-item','network', 'network-list', 'network-list-item','ap', 'ap-list', 'ap-list-item', 'port-list', 'port-list-item', 'flow-list', 'flow-list-item'],
    function () {
        app = new AppRouter();
        Backbone.history.start({pushState: true});
        //console.log("started history")
        
        $(document).ready(function () {
            // trigger Backbone routing when clicking on links, thanks to Atinux and pbnv
            app.navigate("", true);

            window.document.addEventListener('click', function(e) {
                e = e || window.event
                var target = e.target || e.srcElement
                if ( target.nodeName.toLowerCase() === 'a' ) {
                    e.preventDefault()
                    var uri = target.getAttribute('href')
                    app.navigate(uri.substr(1), true)
                }
            });
            window.addEventListener('popstate', function(e) {
                app.navigate(location.pathname.substr(1), true);
            });
            
            // wait for the page to be rendered before loading any data
//            rules.fetch();
//            refreshAPList();
            refreshFireWalls();
            refreshHostNames();
            swl.fetch();
            hl.fetch();
            apl.fetch();
//            setInterval(function () {
//                if(updating) {
//            refreshAPList();
//            refreshFireWalls();
//            refreshHostNames();
//            swl.fetch();
//            hl.fetch();
//            apl.fetch();
//                }
//            }, 3000);
        });
    });
