/*
 * Copyright © 2017-2018 Jyske Bank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dk.jyskebank.tools.enunciate.modules.openapi.yaml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class JsonToYamlHelper {
  private static final Charset JSON_SPEC_ENCODING = StandardCharsets.UTF_8;

  private JsonToYamlHelper() {}
  
  public static String jsonToYaml(String json) {
    try {
      ObjectMapper jsonReader = new ObjectMapper();
      Object obj = jsonReader.readValue(json.getBytes(JSON_SPEC_ENCODING), Object.class);
      
      ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
      return yamlWriter.writeValueAsString(obj);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to convert JSON to YAML:\n" + json, e);
    }
  }
}
