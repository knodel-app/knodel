/*
 * Copyright 2016 Information & Computational Sciences, The James Hutton Institute
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

package uk.ac.hutton.ics.buntata.fragment;

import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import butterknife.*;
import uk.ac.hutton.ics.buntata.R;
import uk.ac.hutton.ics.buntata.util.*;

/**
 * The {@link AboutInformationFragment} shows information about the app in general.
 *
 * @author Sebastian Raubach
 */
public class AboutInformationFragment extends Fragment
{
	private Unbinder unbinder;
	@BindView(R.id.about_information_text)
	TextView text;
	@BindView(R.id.about_information_version)
	TextView version;
	@BindView(R.id.about_information_email)
	CardView web;
	@BindView(R.id.about_information_web)
	CardView email;
	@BindView(R.id.about_information_share)
	CardView share;
	@BindView(R.id.about_information_google_play)
	CardView play;
	@BindView(R.id.about_information_twitter)
	CardView twitter;
	@BindView(R.id.about_information_facebook)
	CardView facebook;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_about_information, container, false);

		unbinder = ButterKnife.bind(this, view);

		try
		{
			version.setText(getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);
		}
		catch (PackageManager.NameNotFoundException e)
		{
			version.setVisibility(View.GONE);
			e.printStackTrace();
		}

		text.setText(StringUtils.fromHtml(getActivity().getString(R.string.about_information_text)));

		return view;
	}

	@OnClick(R.id.about_information_email)
	public void onEmailClicked()
	{
		ShareCompat.IntentBuilder.from(getActivity())
								 .setType("message/rfc822")
								 .addEmailTo(getString(R.string.contact_email_address))
								 .setSubject(getString(R.string.contact_email_subject))
								 .setChooserTitle(R.string.contact_email_dialog_title)
								 .startChooser();
	}

	@OnClick(R.id.about_information_web)
	public void onWebClicked()
	{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website_url))));
	}

	@OnClick(R.id.about_information_facebook)
	public void onFacebookClicked()
	{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook_url))));
	}

	@OnClick(R.id.about_information_twitter)
	public void onTwitterClicked()
	{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.twitter_url))));
	}

	@OnClick(R.id.about_information_share)
	public void onShareClicked()
	{
		ShareCompat.IntentBuilder.from(getActivity())
								 .setType("text/plain")
								 .setText(getString(R.string.google_play_url))
								 .setChooserTitle(R.string.share_chooser_title)
								 .startChooser();
	}

	@OnClick(R.id.about_information_google_play)
	public void onPlayClicked()
	{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.google_play_url))));
	}

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();

		unbinder.unbind();
	}
}
