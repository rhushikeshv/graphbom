// relationship name should be in upper case like EBOM, REVISION_LIKE
create(bike:Part {name:'Bike',level:1,description:'top',type:'Part',rev:1})
create(handlebars:Part{name:'Handle Bars',level:2,description:'component',
                       type:'Part',rev:1})
create(frameassembly:Part{name:'FrameAssembly',level:2,
                          description:'sub-assembly',type:'Part',rev:1})
create(seat:Part{name:'Seat',level:2,description:'component',type:'Part',rev:1})
create(frame:Part{name:'Frame',level:3,description:'sub-assembly',type:'Part',rev:1})
create(wheel:Part{name:'Wheel',level:3,description:'sub-assembly',type:'Part',rev:1})
create(spokes:Part{name:'Spokes',level:4,description:'component',type:'Part',rev:1})
create(tirerim:Part{name:'Tire Rim',level:4,description:'component',type:'Part',rev:1})
create(aluminiumtubing:Part{name:'Aluminium Tubing',level:4,
                            description:'component',type:'Part',rev:1})
create(paint:Part{name:'Paint',level:4,description:'component',type:'Part',rev:1})

create(bike)-[:EBOM {quantity:1}]->(frameassembly)
create(bike)-[:EBOM {quantity:1}]->(seat)
create(bike)-[:EBOM {quantity:1}]->(handlebars)
create(frameassembly)-[:EBOM {quantity:2}]->(wheel)
create(frameassembly)-[:EBOM {quantity:1}]->(frame)
create(wheel)-[:EBOM{quantity:25}]->(spokes)
create(wheel)-[:EBOM{quantity:2}]->(tirerim)
create(frame)-[:EBOM{quantity:'3 ft'}]->(aluminiumtubing)
create(frame)-[:EBOM{quantity:'0.5 gal'}]->(paint)
create(spokes2:Part{name:'Spokes',level:4,description:'component',
                    type:'Part',rev:2})
create(spokes3:Part{name:'Spokes',level:4,description:'component',
                    type:'Part',rev:3})
create(spokes4:Part{name:'Spokes',level:4,description:'component',
                    type:'Part',rev:4})
create(spokes5:Part{name:'Spokes',level:4,description:'component',
                    type:'Part',rev:5})

create(spokes)-[:REVISION]->(spokes2)
create(spokes2)-[:REVISION]->(spokes3)
create(spokes3)-[:REVISION]->(spokes4)
create(spokes4)-[:REVISION]->(spokes5)


create(Bolts:Part{name:'Bolts',level:1,description:'Bolts',rev:1,quantity:200,unit_price:18,units_in_stock:39,units_on_order:0,reorder_level:10,discontinued:'FALSE',type:'Part'})