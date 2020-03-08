// relationship name should be in upper case like EBOM, REVISION_LIKE
create(bike:Part {name:'Bike',level:1,description:'top',type:'Part',partNumber:1})
create(handlebars:Part{name:'Handle Bars',level:2,description:'component',
                       type:'Part',partNumber:1})
create(frameassembly:Part{name:'FrameAssembly',level:2,
                          description:'sub-assembly',type:'Part',partNumber:1})
create(seat:Part{name:'Seat',level:2,description:'component',type:'Part',partNumber:1})
create(frame:Part{name:'Frame',level:3,description:'sub-assembly',type:'Part',partNumber:1})
create(wheel:Part{name:'Wheel',level:3,description:'sub-assembly',type:'Part',partNumber:1})
create(spokes:Part{name:'Spokes',level:4,description:'component',type:'Part',partNumber:1})
create(tirerim:Part{name:'Tire Rim',level:4,description:'component',type:'Part',partNumber:1})
create(aluminiumtubing:Part{name:'Aluminium Tubing',level:4,
                            description:'component',type:'Part',partNumber:1})
create(paint:Part{name:'Paint',level:4,description:'component',type:'Part',partNumber:1})

create(bike)-[:EBOM {quantity:1}]->(frameassembly)
create(bike)-[:EBOM {quantity:1}]->(seat)
create(bike)-[:EBOM {quantity:1}]->(handlebars)
create(frameassembly)-[:EBOM {quantity:2}]->(wheel)
create(frameassembly)-[:EBOM {quantity:1}]->(frame)
create(wheel)-[:EBOM{quantity:25}]->(spokes)
create(wheel)-[:EBOM{quantity:2}]->(tirerim)
create(frame)-[:EBOM{quantity:'3 ft'}]->(aluminiumtubing)
create(frame)-[:EBOM{quantity:'0.5 gal'}]->(paint)