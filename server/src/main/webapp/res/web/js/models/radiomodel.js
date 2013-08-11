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

window.Radio = Backbone.Model.extend({
	initialize:function () {
		var self = this;
		this.networks = new NetworkCollection();
	    $.when($.ajax({
	        url:hackBase + "/networks/" + self.id,
	        dataType:'json',
	        success:function (data) {
	            //console.log("fetched  switch " + self.id + " ports");
	            //console.log(data[self.id]);
	            var old_ids = self.networks.pluck('id');
	            //console.log("old_ids" + old_ids);
	
	            // create port models
	            _.each(data, function(p) {
	                // workaround for REST serialization signed/unsigned bug
	                old_ids = _.without(old_ids, p.id);
	                var m = self.networks.get(p.id);
	                if(m) {
	                    m.set(p, {silent: true});
	                } else {
	                    self.networks.add(p, {silent: true});
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
	        self.networks.trigger('add'); // batch redraws
	    });
    },

    fetch:function () {
        this.initialize()
    },
});

window.RadioCollection = Backbone.Collection.extend({
    model:Radio,
    initialize:function () {}
});