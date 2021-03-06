/*
 * Copyright 2004-2013 H2 Group. Multiple-Licensed under the H2 License,
 * Version 1.0, and under the Eclipse Public License, Version 1.0
 * (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.lealone.sql.ddl;

import org.lealone.db.ServerSession;
import org.lealone.db.result.Result;
import org.lealone.sql.StatementBase;

/**
 * This class represents a non-transaction statement, for example a CREATE or DROP.
 */
public abstract class DefineStatement extends StatementBase {

    /**
     * The transactional behavior. The default is disabled, meaning the command
     * commits an open transaction.
     */
    protected boolean transactional;

    /**
     * Create a new command for the given session.
     *
     * @param session the session
     */
    protected DefineStatement(ServerSession session) {
        super(session);
    }

    @Override
    public Result getMetaData() {
        return null;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    @Override
    public boolean isTransactional() {
        return transactional;
    }

    @Override
    public int getPriority() {
        priority = MIN_PRIORITY;
        return priority;
    }
}
