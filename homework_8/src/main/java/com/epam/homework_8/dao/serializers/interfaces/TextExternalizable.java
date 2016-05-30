package com.epam.homework_8.dao.serializers.interfaces;

import java.io.*;

public interface TextExternalizable extends Serializable {

    void writeTextExternal(Writer out) throws IOException;

    void readTextExternal(Reader in) throws IOException, ClassNotFoundException;

}
