package com.gwccnet.web.utilities;

import java.util.Stack;

public class BreadCrumbHandler
{
	Stack<BreadCrumb> breadCrumbs = new Stack<BreadCrumb>();
	public BreadCrumbHandler()
	{
		
	}

	public synchronized void pushBreadCrumb(BreadCrumb bc)
	{
		if (!breadCrumbs.contains(bc))
		{
			breadCrumbs.push(bc);
		}
		else
		{
			popAllCrumbsFrom(bc);
		}
	}
	public synchronized void createBreadCrumb(String url, String description)
	{
		pushBreadCrumb(new BreadCrumb(url,description));
	}
	public synchronized BreadCrumb popBreadCrumb()
	{
		return breadCrumbs.pop();
	}
	public synchronized void popAllCrumbsFrom(BreadCrumb bc)
	{
		int index = breadCrumbs.indexOf(bc);
		if (index > 0)
		{
			int popCount = breadCrumbs.size() - index;
			for (int i = 1; i < popCount;++i)
			{
				breadCrumbs.pop();
			}
		}
	}
	public void clearAllCrumbs()
	{
		breadCrumbs.clear();
	}

	public Stack<BreadCrumb> getBreadCrumbs()
	{
		return breadCrumbs;
	}

	public void setBreadCrumbs(Stack<BreadCrumb> breadCrumbs)
	{
		this.breadCrumbs = breadCrumbs;
	}
}
