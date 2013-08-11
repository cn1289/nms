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

window.TopologyView = Backbone.View.extend({
    initialize:function () {
        this.template = _.template(tpl.get('topology'));
        this.model.bind("change", this.render, this);
        this.hosts = this.options.hosts.models;
//        this.ap_hosts=[];
//        this.aps=this.options.aps.models;
        this.host_links = [];
    },

    render:function (eventName) {
        $(this.el).html(this.template());
        var width = 900,
        height = 600;
        var color = d3.scale.category20();
        var force = d3.layout.force()
                         .charge(-600)
                         .linkDistance(100)
                         .size([width, height]);
        var svg = d3.select("#topology-graph").append("svg")
                    .attr("width", width)
                    .attr("height", height);
        if(this.model.nodes) {
            for (var i = 0; i < this.model.nodes.length; i++) {
            	if(this.model.nodes[i].type && this.model.nodes[i].type==1){
            		this.model.nodes[i].group = 3;//AP
            	}else{
            		this.model.nodes[i].group = 1;//Switch
            	}
                this.model.nodes[i].id = this.model.nodes[i].name;
            }
            
//            for (var t = 0; t < this.aps.length; t++) {
//            	var apId=this.aps[t].attributes['id'][0]
//            	if(this.aps[t].attributes['hosts'].length > 0){
//            		var hs=this.aps[t].attributes['hosts'];
//	            	 for (var i = 0; i < hs.length; i++) {
//	            		 var m=hs[i].mac;
//	            		 var hostName=getHostName(m);
//	            		 var aph={
//	            				 'id':m,
//	            				 'name':hostName,
//	            				 'group':'4'
//	            					 };
//		                    this.ap_hosts.push(aph);
//		                    var target;
//		                    for (var y = 0; y < this.model.nodes.length; y++) {
//	                        	if(this.model.nodes[y].group == 3 && this.model.nodes[y].id==apId){
//	                        		targ= this.model.nodes[y];
//	                        	}
//	                        }
//		                    var aplink = {source:aph,target:targ,value:10};
//		                    //console.log(link);
//		                    if ( aplink.source && aplink.target) {
//		                    	this.host_links.push(aplink);
//		                    } else {
//		                        console.log("Error: skipping aplink with undefined stuff!")
//		                    }
//		                
//		            }
//            	}
//            }
            
            for (var i = 0; i < this.hosts.length; i++) {
                host = this.hosts[i];
                console.log("host.name="+host.get("name"));
                if (host.attributes['ipv4'].length > 0) {
                    host.name = host.attributes['ipv4'][0] + "\n" + host.get("name");
                } else {
                    host.name = host.get("name");
                }
                host.group = 2;
            }
            
            var all_nodes = this.model.nodes.concat(this.hosts);
            var all_nodes_map = [];
            
            _.each(all_nodes, function(n) {
                all_nodes_map[n.id] = n;
            });
            
            for (var i = 0; i < this.hosts.length; i++) {
                host = this.hosts[i];
                //for (var j = 0; j < host.attributes['attachmentPoint'].length; j++) {
                for (var j = 0; j < 1; j++) { // FIXME hack to ignore multiple APs
                    var link = {source:all_nodes_map[host.id],
                                target:all_nodes_map[host.attributes['attachmentPoint'][j]['switchDPID']],
                                value:10};
                    //console.log(link);
                    if ( link.source && link.target) {
                        this.host_links.push(link);
                    } else {
                        console.log("Error: skipping link with undefined stuff!")
                    }
                }
            }
            
            var all_links = this.model.links.concat(this.host_links);
            
//            all_nodes=all_nodes.concat(this.ap_hosts);
            
            force.nodes(all_nodes).links(all_links).start();
            var link = svg.selectAll("line.link").data(all_links).enter()
                          .append("line").attr("class", "link")
                          .style("stroke", function (d) { return "black"; });
            var node = svg.selectAll(".node").data(all_nodes)
                          .enter().append("g")
                          .attr("class", "node")
                          .call(force.drag);
            
            node.append("image")
                .attr("xlink:href", function (d) {
                	var r; 
                	if(d.group==1) {
                		r= "/nms/img/switch.png";
                	}else if(d.group==3){
                		r="/nms/img/ap.png";
                	}else if(d.group==2){
                		r="/nms/img/host.png";
                	}else{
                		r="/nms/img/laptop.png";
                	}
                return r;	
                })
                .attr("x", -16).attr("y", -16)
                .attr("width", 32).attr("height", 32);
            node.append("text").attr("dx", 20).attr("dy", ".35em")
                .text(function(d) { return d.name });
            node.on("click", function (d) {
            	if(d.group==3){
            		alert("d.group="+d.group);
            	}else{ 
            		$("#toolbox").show();
            		if(d.group==1){
                		$("#toolbox").html($("#switchInfo").html());
                		console.log('clicked switch '+d.name);
            		}else if(d.group==2 || d.group==4){
            			$("#toolbox").html($("#hostInfo").html());
                		$("#src-mac").val(d.id);
                		$("#hostName").val(d.name);
                		
                		var status;
                		if(d.group==2){
                			status=getHttpPortStatus(d.get("longMac"));
                		}else{
                			status=getHttpPortStatus(d.longMac);
                		}
                		
                    	console.log("httpPortStatus"+status);
                    	$("#action").val(status);
                    	$("#vendor").html(d.vendor);
                		console.log('clicked host'+d.id);
            		}
            	}
            });
            node.append("title").text(function(d) { return d.name; });
            
            
            force.on("tick", function() {
            	
                link.attr("x1", function(d) { return d.source.x; })
                    .attr("y1", function(d) { return d.source.y; })
                    .attr("x2", function(d) { return d.target.x; })
                    .attr("y2", function(d) { return d.target.y; });
                node.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
                
            });
        }
        return this;
    }
});
