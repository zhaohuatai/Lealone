/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.test.db;

import org.lealone.db.ConnectionInfo;
import org.lealone.db.Database;
import org.lealone.db.DatabaseEngine;
import org.lealone.db.ServerSession;
import org.lealone.db.result.Result;
import org.lealone.test.UnitTestBase;

public class DbObjectTestBase extends UnitTestBase {
    public static final String DB_NAME = "DbObjectTest";

    protected Database db;
    protected ServerSession session;

    protected String sql;

    public DbObjectTestBase() {
        setInMemory(true);
        setEmbedded(true);
        addConnectionParameter("DATABASE_TO_UPPER", "false"); // 不转成大写
        ConnectionInfo ci = new ConnectionInfo(getURL(DB_NAME));
        session = DatabaseEngine.createSession(ci);
        db = session.getDatabase();
        session = db.getSystemSession();
    }

    public int executeUpdate(String sql) {
        return session.prepareStatementLocal(sql).executeUpdate();
    }

    public Result executeQuery(String sql) {
        return session.prepareStatementLocal(sql).executeQuery(0, false);
    }

    // index从1开始
    public int getInt(Result result, int index) {
        if (result.next())
            return result.currentRow()[index - 1].getInt();
        else
            return -1;
    }

    public int getInt(String sql, int index) {
        return getInt(executeQuery(sql), index);
    }
}