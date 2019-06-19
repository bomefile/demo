/*
 * Copyright 2018-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test;

import com.test.common.Foo1;
import com.test.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gary Russell
 * @since 2.2.1
 */
@RestController
public class Controller {



	@Autowired
	private ConsumeService consumeService;

	@PostMapping(path = "/send/foos/{what}")
	public void sendFoo(@PathVariable String what) {
		consumeService.sendMq(what);
	}

	@GetMapping(path = "/handle/baktopic")
	public void handlerBakTopic(Long second){
		consumeService.handleTopic(second);
	}



}
