ServerEvents.recipes(event => {
    event.remove({output:'ends_delight:end_stone_knife'})
    event.shaped(Item.of('ends_delight:end_stone_knife',1),
    [
        'L  ',
        'O  ',
        '   '
    ],
    {
        L: 'minecraft:end_stone',
        O: 'minecraft:stick'
    })
    event.custom(
       {
  "type": "farmersdelight:cutting",
  "ingredients": [
    {
      "item": "end_reborn:chorus_spine"
    }
  ],
  "result": [
    {
      "item": "minecraft:chorus_fruit",
      "count": 2
    }
  ],
  "tool": {
    "tag": "c:tools/knives"
  }
}
    )
});