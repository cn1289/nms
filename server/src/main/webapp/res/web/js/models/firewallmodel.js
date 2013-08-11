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

window.FireWall = Backbone.Model.extend({

    defaults: {
        dl_src: '',
        action: 'DENY '
    },

    // initialize:function () {}

});

window.FireWallCollection = Backbone.Collection.extend({

    model:FireWall,

    fetch:function () {
        var self = this;
        //console.log("fetching FireWall list")
        self.reset();
        $.ajax({
            url:hackBase + "wm/firewall/rules/json",
            dataType:'json',
            success:function (data) {
                console.log("fetched  FireWall list: " + data.length);
                console.log(data);
                //console.log("old_ids" + old_ids);
                _.each(data, function(h) {
                	console.log("dl_src is"+h.dl_src);
                	console.log("firewall is"+h.toString());
                	 self.add(h, {silent: true});
                });
                // old_ids now holds FireWalls that no longer exist; remove them
                //console.log("old_ids" + old_ids);
                self.trigger('add'); // batch redraws
            }
        });

    },
});
