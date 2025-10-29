package dev.saraki.meovo.core.command

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.mainCommand
import taboolib.expansion.createHelper


/**
 * Core 核心命令：插件全局管理入口
 * 支持重载配置、查看状态、控制模块等核心操作
 */
@CommandHeader(
    name = "meovo",
    aliases = ["meo"],
    description = "Meoveo 管理命令",
    permission = "meovo.admin",
    permissionDefault = PermissionDefault.TRUE
)
object CoreCommand {

    /**
     * 主命令：显示帮助信息
     */
    @CommandBody
    val main = mainCommand {
        createHelper()
    }
}