/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.scaling.mysql;

import org.apache.shardingsphere.scaling.core.job.position.LogPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Binlog Position.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BinlogPosition implements LogPosition<BinlogPosition> {
    
    private static final long serialVersionUID = -4917415481787093677L;
    
    private final String filename;
    
    private final long position;
    
    private long serverId;
    
    @Override
    public final int compareTo(final BinlogPosition binlogPosition) {
        if (null == binlogPosition) {
            return 1;
        }
        long o1 = toLong();
        long o2 = binlogPosition.toLong();
        return Long.compare(o1, o2);
    }
    
    private long toLong() {
        return Long.parseLong(filename.substring(filename.lastIndexOf(".") + 1)) << 32 | position;
    }
}
