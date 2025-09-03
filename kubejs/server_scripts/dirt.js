ServerEvents.recipes(event => {
 event.shaped(Item.of('minecraft:dirt',2),
    [
        'LLL',
        'OOO',
        'OOO'
    ],
    {
        L: 'end_reborn:chorus_spine',
        O: 'minecraft:end_stone'
    })    
    event.custom(
       {
  "type": "techreborn:centrifuge",
  "power": 5,
  "time": 400,
  "ingredients": [
    {
      "item": "minecraft:end_stone"
    }
  ],
  "results": [
    {
      "item": "minecraft:dirt",
      "count": 1
    },
    {
      "item": "techreborn:saltpeter_dust",
      "count": 2
    },
    {
      "item": "techreborn:rubber",
      "count": 2
    }
   
  ]
}


    )
});

