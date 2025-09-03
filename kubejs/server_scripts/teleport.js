// 监听玩家重生事件
PlayerEvents.respawned(event => {
    const player = event.player;
    const dimensionId = player.level.dimension;
    if (dimensionId === 'minecraft:overworld') {
        let x, z, y;
        const savedPos = player.persistentData.rocket_intro_spawn_pos;
        x = savedPos.x;
        z = savedPos.z;
        y = savedPos.y;
        const name = event.player.username;
        event.server.runCommandSilent(`execute in minecraft:the_end run tp ${name} ${x} ${y} ${z}`);
    }
});