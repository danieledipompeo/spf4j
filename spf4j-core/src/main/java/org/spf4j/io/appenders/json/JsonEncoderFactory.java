/*
 * Copyright (c) 2001-2017, Zoltan Farkas All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Additionally licensed with:
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spf4j.io.appenders.json;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.ExtendedJsonEncoder;
import org.spf4j.io.AppendableWriter;

/**
 * @author Zoltan Farkas
 */
public final class JsonEncoderFactory {

  private static final EncoderSupplier  DECODER_SUPPLIER;

  static {

    Class<?> clasz  = null;
    try {
      clasz = Class.forName("org.apache.avro.io.ExtendedJsonDecoder");
    } catch (ClassNotFoundException ex) {
      // Extended decoder not available.
    }
    if (clasz == null) {
      DECODER_SUPPLIER = new EncoderSupplier() {

        private EncoderFactory factory = EncoderFactory.get();

        @Override
        public Encoder getEncoder(final Schema writerSchema, final OutputStream os) throws IOException {
          return factory.jsonEncoder(writerSchema, os);
        }

        @Override
        public Encoder getEncoder(final Schema writerSchema, final Appendable os) throws IOException {
          return factory.jsonEncoder(writerSchema, new AppendableWriter(os));
        }
      };
    } else {

       DECODER_SUPPLIER = new EncoderSupplier() {
         @Override
         public Encoder getEncoder(final Schema writerSchema, final OutputStream os) throws IOException {
           return new ExtendedJsonEncoder(writerSchema, os);
         }

         @Override
         public Encoder getEncoder(final Schema writerSchema, final Appendable os) throws IOException {
           return new ExtendedJsonEncoder(writerSchema,
                   Schema.FACTORY.createGenerator(new AppendableWriter(os)));
         }
       };
    }
  }

  private JsonEncoderFactory() { }

  interface EncoderSupplier {
    Encoder getEncoder(Schema writerSchema, OutputStream os) throws IOException;

    Encoder getEncoder(Schema writerSchema, Appendable os) throws IOException;

  }

  public static Encoder getEncoder(final Schema writerSchema, final OutputStream os) throws IOException {
    return DECODER_SUPPLIER.getEncoder(writerSchema, os);
  }

  public static Encoder getEncoder(final Schema writerSchema, final Appendable os) throws IOException {
    return DECODER_SUPPLIER.getEncoder(writerSchema, os);
  }


}
