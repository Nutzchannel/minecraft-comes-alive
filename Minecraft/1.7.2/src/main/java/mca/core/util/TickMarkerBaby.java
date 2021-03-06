/*******************************************************************************
 * TickMarkerBaby.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MCA Minecraft Mod license.
 ******************************************************************************/

package mca.core.util;

import com.radixshock.radixcore.entity.ITickableEntity;
import com.radixshock.radixcore.logic.TickMarker;

/**
 * TickMarker intended to cause a villager's baby to grow.
 */
public class TickMarkerBaby extends TickMarker
{
	/**
	 * Constructor
	 * 
	 * @param owner The owner of this TickMarker.
	 * @param durationInTicks The duration of this TickMarker.
	 */
	public TickMarkerBaby(ITickableEntity owner, int durationInTicks)
	{
		super(owner, durationInTicks);
	}

	@Override
	public void onComplete()
	{
		//TODO
	}
}
