package com.epam.homework_8.serializers;

import java.io.*;

public interface TextExternalizable {

    void writeExternal(Writer out) throws IOException;

    void readExternal(Reader in) throws IOException, ClassNotFoundException;

}
