package com.dujay.generator.model.methods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dujay.generator.model.constants.ConstantPool;
import com.dujay.generator.writers.ByteStreamWriter;
import com.dujay.generator.writers.WriteableByteStream;

public class MethodPool implements WriteableByteStream {
  
  private List<Method> methods;
  private ByteArrayOutputStream bytes;
  private ConstantPool constants;
  
  public MethodPool(ConstantPool constants) {
    methods = new ArrayList<Method>();
    bytes = new ByteArrayOutputStream();
    this.constants = constants;
  }
  
  public void addMethod(Method m) {
    methods.add(m);
  }
  
  public void prepareStream() throws IOException {
    ByteStreamWriter writer = new ByteStreamWriter(bytes);
    for(Method m : methods) {
      m.prepareStream(constants);
      writer.appendStream(m);
    }
  }

  @Override
  public ByteArrayOutputStream getStream() {
    return bytes;
  }

  public int length() {
    return methods.size();
  }

}