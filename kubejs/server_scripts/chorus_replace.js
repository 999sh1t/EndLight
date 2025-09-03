ServerEvents.recipes(event => {
event.remove({output:'end_reborn:chorus_block'})
     event.shaped(Item.of('betterend:pythadendron_log',1),
    [
        'LLL',
        'LLL',
        'LLL'
    ],
    {
        L: 'end_reborn:chorus_spine'
        
    })

});