/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/6/9
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package kr.co.kcd.campaign.config;

import java.time.Duration;
import java.util.List;
import kr.co.kcd.campaign.model.AdGroup;
import lombok.Getter;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhcacheConfing {
  private static volatile EhcacheConfing instance;
  private CacheManager ehcacheManager;
  @Getter private Cache<String, List<AdGroup>> adGroupsCache;

  private EhcacheConfing() {
    this.ehcacheManager =
        CacheManagerBuilder.newCacheManagerBuilder()
            .withCache(
                "adGroupsCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class,
                        (Class<List<AdGroup>>) (Class<?>) List.class,
                        ResourcePoolsBuilder.heap(100))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(5))))
            .build(true);
    adGroupsCache =
        ehcacheManager.getCache(
            "adGroupsCache", String.class, (Class<List<AdGroup>>) (Class<?>) List.class);
  }

  // Public method to provide access to the instance
  public static EhcacheConfing getInstance() {
    if (instance == null) {
      synchronized (EhcacheConfing.class) {
        instance = new EhcacheConfing();
      }
    }
    return instance;
  }

  public void close() {
    this.ehcacheManager.close();
  }
}
