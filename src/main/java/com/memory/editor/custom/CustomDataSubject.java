package com.memory.editor.custom;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.editor.AbstractConcurrentSubject;
import com.memory.editor.Aspect;
import com.memory.editor.database.NotifyVisitor;
import com.memory.persistence.entity.CustomData;
import com.memory.persistence.repository.CustomDataRepository;

@Service
public class CustomDataSubject extends AbstractConcurrentSubject<CustomData, Long> {

	@Autowired
	public CustomDataSubject(CustomDataRepository repository) {
		super(repository);
	}

	@PostConstruct
	@Override
	public void init() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(3 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					CustomData element = new CustomData();
					element.setData1(new Random().nextLong());
					element.setData2(new Random().nextLong());
					NotifyVisitor<CustomData, Long> visitor = new NotifyVisitor<CustomData, Long>(getRepository(), Aspect.CREATE, element, getObservers());
					getProcesser().process(visitor);
				}

			}

		});
		t.start();
	}

}
