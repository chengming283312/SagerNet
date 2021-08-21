/******************************************************************************
 *                                                                            *
 * Copyright (C) 2021 by nekohasekai <sekai@neko.services>                    *
 * Copyright (C) 2021 by Max Lv <max.c.lv@gmail.com>                          *
 * Copyright (C) 2021 by Mygod Studio <contact-shadowsocks-android@mygod.be>  *
 *                                                                            *
 * This program is free software: you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation, either version 3 of the License, or          *
 *  (at your option) any later version.                                       *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with this program. If not, see <http://www.gnu.org/licenses/>.       *
 *                                                                            *
 ******************************************************************************/

package io.nekohasekai.sagernet.bg.proto

import io.nekohasekai.sagernet.bg.AbstractInstance
import io.nekohasekai.sagernet.fmt.socks.SOCKSBean
import kotlinx.coroutines.CoroutineScope
import libcore.Socks4To5Instance

class Socks4To5Instance(val server: SOCKSBean, val port: Int) : AbstractInstance {

    lateinit var point: Socks4To5Instance

    override fun launch() {
        point = Socks4To5Instance(
            port.toLong(),
            server.finalAddress,
            server.finalPort.toLong(),
            server.username,
            server.protocol == SOCKSBean.PROTOCOL_SOCKS4A
        )
        point.start()
    }

    override fun destroy(scope: CoroutineScope) {
        if (::point.isInitialized) point.close()
    }
}