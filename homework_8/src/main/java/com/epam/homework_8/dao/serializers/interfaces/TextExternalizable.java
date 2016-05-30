package com.epam.homework_8.dao.serializers.interfaces;

import java.io.*;

public interface TextExternalizable extends Serializable {

    void writeTextExternal(BufferedWriter out) throws IOException;

    void readTextExternal(BufferedReader in) throws IOException;

}
