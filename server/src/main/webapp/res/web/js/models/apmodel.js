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

window.AP = Backbone.Model.extend({
	initialize:function () {
		var self = this;
		this.radios = new RadioCollection();
	    $.when($.ajax({
	        url:hackBase + "/radios/" + self.id,
	        dataType:'json',
	        success:function (data) {
	            //console.log("fetched  switch " + self.id + " ports");
	            //console.log(data[self.id]);
	            var old_ids = self.radios.pluck('id');
	            //console.log("old_ids" + old_ids);
	
	            // create port models
	            _.each(data, function(p) {
	                // workaround for REST serialization signed/unsigned bug
	                old_ids = _.without(old_ids, p.id);
	                var m = self.radios.get(p.id);
	                if(m) {
	                    m.set(p, {silent: true});
	                } else {
	                    self.radios.add(p, {silent: true});
	                }
	                //console.log(p);
	            });
	            
	            // old_ids now holds ports that no longer exist; remove them
	            //console.log("old_ids" + old_ids);
	            _.each(old_ids, function(p) {
	                console.log("removing radio " + p);
	                self.remove({id:p});
	            });
	        }
	    })).done(function() {
	        self.radios.trigger('add'); // batch redraws
	    });
    },

    fetch:function () {
        this.initialize()
    },
});

window.APCollection = Backbone.Collection.extend({

    model:AP,

    fetch:function () {
        var self = this;
        $.ajax({
        	type: "get",
            url:hackBase + "apList",
            dataType:'json',
            success:function (data) {
                console.log("fetched  ap list: " + data.length);
                console.log(data);
                var old_ids = self.pluck('id');
                _.each(data, function(sw) {
                    old_ids = _.without(old_ids, sw.id);
                    console.log("ap vendor:"+sw.vendor.name);
                    self.add({id: sw.id, macAddress: sw.macAddress,
                        type: sw.apType.name,vendor:sw.vendor.name});
                });
                console.log("ap list size" + self.length);
                // old_ids now holds switches that no longer exist; remove them
                //console.log("old_ids" + old_ids);
                _.each(old_ids, function(sw) {
                    console.log("removing ap " + sw);
                    self.remove({id:sw});
                });
            },
        });
        }
});
