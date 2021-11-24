package com.song.db;

import com.song.db.util.DbUtil;
import org.junit.Test;

import java.io.File;

public class DbUtilTest {
    @Test
    public void testBackup() {
        File file = new File("test.sql");
        DbUtil.backup(file, "root", "root", "gradu");
    }

//    这个测试用例会重置gradu数据库，所以比较危险，请开发者注意
//    @Test
    public void testLoad() {
        File file = new File("test.sql");
        DbUtil.load(file, "root", "root", "gradu");
    }
}
