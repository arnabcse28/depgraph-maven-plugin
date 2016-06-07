package com.github.ferstl.depgraph.graph.style;

import java.io.IOException;
import java.util.Map;
import com.github.ferstl.depgraph.dot.AttributeBuilder;
import com.github.ferstl.depgraph.graph.NodeResolution;
import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class StyleConfiguration {

  NodeConfiguration defaultNode = new NodeConfiguration();
  EdgeConfiguration defaultEdge = new EdgeConfiguration();
  Map<String, NodeConfiguration> scopedNodes = ImmutableMap.of("compile", new NodeConfiguration(), "test", new NodeConfiguration());
  Map<NodeResolution, EdgeConfiguration> edgeTypes = ImmutableMap.of(NodeResolution.INCLUDED, new EdgeConfiguration(), NodeResolution.OMITTED_FOR_DUPLICATE, new EdgeConfiguration());


  public static void main(String[] args) {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
        .enableComplexMapKeySerialization()
        .registerTypeAdapter(NodeResolution.class, new TypeAdapter<NodeResolution>() {

          @Override
          public void write(JsonWriter out, NodeResolution value) throws IOException {
            out.value(value.name().replace('_', '-').toLowerCase());
          }

          @Override
          public NodeResolution read(JsonReader in) throws IOException {
            String value = in.nextString();
            return NodeResolution.valueOf(value.replace('-', '_').toUpperCase());
          }
        })
        .setPrettyPrinting()
        .create();

    System.out.println(gson.toJson(new StyleConfiguration()));
  }

  static class NodeConfiguration implements GlobalConfigurer, SingleConfigurer {

    AbstractNode shape = new Polygon();
    String color = "red";
    Font font = new Font();

    @Override
    public void configure(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }

    @Override
    public void configureGlobally(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }
  }

  static class EdgeConfiguration implements GlobalConfigurer, SingleConfigurer {

    String style = "dotted";
    String color = "black";
    Font font = new Font();

    @Override
    public void configure(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }

    @Override
    public void configureGlobally(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }
  }

  static class Font implements GlobalConfigurer, SingleConfigurer {

    String color = "black";
    int size = 14;
    String name = "Helvetica";

    @Override
    public void configure(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }

    @Override
    public void configureGlobally(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }
  }

  abstract static class AbstractNode implements GlobalConfigurer, SingleConfigurer {

    final String type;
    String color = "black";
    String style = "rounded";
    Font font = new Font();

    AbstractNode(String type) {
      this.type = type;
    }

    @Override
    public void configureGlobally(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }

    @Override
    public void configure(AttributeBuilder builder) {
      // TODO Auto-generated method stub

    }

  }

  static class Polygon extends AbstractNode {

    int sides = 4;


    Polygon() {
      super("polygon");
    }

    @Override
    public void configureGlobally(AttributeBuilder builder) {
      // TODO Auto-generated method stub
      super.configureGlobally(builder);
    }

    @Override
    public void configure(AttributeBuilder builder) {
      // TODO Auto-generated method stub
      super.configure(builder);
    }
  }

  static class Ellipse extends AbstractNode {

    Ellipse() {
      super("ellipse");
    }

    @Override
    public void configureGlobally(AttributeBuilder builder) {
      // TODO Auto-generated method stub
      super.configureGlobally(builder);
    }

    @Override
    public void configure(AttributeBuilder builder) {
      // TODO Auto-generated method stub
      super.configure(builder);
    }
  }
}