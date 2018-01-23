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
package org.spf4j.base;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.spf4j.concurrent.DefaultExecutor;

/**
 *
 * @author Zoltan Farkas
 */
public class ExecutionContextTest {

  @Test
  public void testExecutionContext() throws InterruptedException, ExecutionException {
    try (ExecutionContext ec = ExecutionContext.start(TimeSource.getDeadlineNanos(10, TimeUnit.SECONDS))) {
      long unitsToDeadline = ExecutionContext.current().getUnitsToDeadline(TimeUnit.SECONDS);
      Assert.assertThat(unitsToDeadline, Matchers.lessThanOrEqualTo(10L));
      Assert.assertThat(unitsToDeadline, Matchers.greaterThanOrEqualTo(9L));
      Future<?> submit = DefaultExecutor.INSTANCE.submit(() -> {
        try (ExecutionContext subCtx = ec.subCtx()) {
          long utd = ExecutionContext.current().getUnitsToDeadline(TimeUnit.SECONDS);
          Assert.assertThat(utd, Matchers.lessThanOrEqualTo(10L));
          Assert.assertThat(utd, Matchers.greaterThanOrEqualTo(9L));
          Assert.assertEquals(ec, subCtx.getParent());
        }
        Assert.assertNull(ExecutionContext.current());
      });
      submit.get();
    }
    Assert.assertNull(ExecutionContext.current());
  }

  @Test
  public void testExecutionContext2() {
    try (ExecutionContext start = ExecutionContext.start(10, TimeUnit.SECONDS)) {
      long secs = start.getUnitsToDeadline(TimeUnit.SECONDS);
      Assert.assertTrue(secs >= 9);
      Assert.assertTrue(secs <= 10);
    }
  }


}
