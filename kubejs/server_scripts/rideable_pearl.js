ItemEvents.rightClicked('minecraft:ender_pearl', event => {
  player.runCommandSilent('effect give @s minecraft:absorption 10 0')
})