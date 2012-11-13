package org.paneris.melati.gvis;

import java.lang.IllegalArgumentException;
import java.util.Map;

import org.melati.poem.PoemTypeFactory;

import com.google.common.collect.Maps;
import com.google.visualization.datasource.datatable.value.ValueType;


/**
 * @author timp
 * @since 13 Nov 2012
 * see com.google.visualization.datasource.datatable.value.ValueType
 */
public enum PoemGvisType {
  TROID(PoemTypeFactory.TROID.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }

    @Override
    String jsonValue(Object value) { 
      if (value == null )
        return "null";
      else
        return "\"" + value.toString() + "\"";
    }
  },
  DELETED(PoemTypeFactory.DELETED.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.BOOLEAN;
    }
    String jsonValue(Object value) { 
      if (value == null )
        return "null";
      else
        return value.toString();
    }
  },
  TYPE(PoemTypeFactory.TYPE.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
  },
  
  /* Base type factories. */
   BOOLEAN(PoemTypeFactory.BOOLEAN.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.BOOLEAN;
    }
  },
   INTEGER(PoemTypeFactory.INTEGER.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }

    @Override
    String jsonValue(Object value) { 
      if (value == null )
        return "null";
      else
        return value.toString();
    }
  },
   DOUBLE(PoemTypeFactory.DOUBLE.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
    @Override
    String jsonValue(Object value) { 
      if (value == null )
        return "null";
      else
        return value.toString();
    }
  },
   LONG(PoemTypeFactory.LONG.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
    @Override
    String jsonValue(Object value) { 
      if (value == null )
        return "null";
      else
        return value.toString();
    }
  },
   BIGDECIMAL(PoemTypeFactory.BIGDECIMAL.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
    @Override
    String jsonValue(Object value) { 
      if (value == null )
        return "null";
      else
        return value.toString();
    }
  },
   STRING(PoemTypeFactory.STRING.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.TEXT;
    }
  },
   PASSWORD(PoemTypeFactory.PASSWORD.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.TEXT;
    }
  },
   DATE(PoemTypeFactory.DATE.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.DATE;
    }
  },
   TIMESTAMP(PoemTypeFactory.TIMESTAMP.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.DATETIME;
    }
  },
   TIME(PoemTypeFactory.TIME.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.TIMEOFDAY;
    }
  },
   BINARY(PoemTypeFactory.BINARY.getCode()) {
    @Override
    ValueType gvisType() {
      throw new IllegalArgumentException("Binary poem type cannot be exported to google viualisation API.");
    }
  },

   /** Poem factories. */
   DISPLAYLEVEL(PoemTypeFactory.DISPLAYLEVEL.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
  },
   SEARCHABILITY(PoemTypeFactory.SEARCHABILITY.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
  },
   INTEGRITYFIX(PoemTypeFactory.INTEGRITYFIX.getCode()) {
    @Override
    ValueType gvisType() {
      return ValueType.NUMBER;
    }
  };
  
  final Integer poemType;
  PoemGvisType(Integer poemType) { 
    this.poemType = poemType;
  }
  
  public Integer getPoemType(){ 
    return poemType;
  }
  public PoemTypeFactory getPeomTypeFactory() { 
    return PoemTypeFactory.forCode(null, poemType);
  }
  
  abstract ValueType gvisType();
  
  String gvisJsonTypeName() { 
    return gvisType().getTypeCodeLowerCase();
  }
  
  String jsonValue(Object value) { 
    if (value == null )
      return "null";
    else
      return "\"" + value.toString() + "\"";
  }
  
  public static PoemGvisType from(Integer poemType){ 
    return typeCodeToPoemGvisType.get(poemType);
  }
  private static Map<Integer, PoemGvisType> typeCodeToPoemGvisType;
  static {
    typeCodeToPoemGvisType = Maps.newHashMap();
    for (PoemGvisType type : PoemGvisType.values()) {
      typeCodeToPoemGvisType.put(type.poemType, type);
    }
  }

}
