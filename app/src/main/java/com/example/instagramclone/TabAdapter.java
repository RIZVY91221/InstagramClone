package com.example.instagramclone;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter
{
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int tabPosition)
    {
      switch (tabPosition){
          case 0:
              return new HomeTab();
          case 1:
              return new NotificationTab();
          case 2:
              return new ProfileTab();
          case 3:
              return new User();


              default:
                  return null;
      }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "Notification";
            case 2:
                return "Profile";
            case 3:
                return "User";
                default:
                    return null;
        }
    }
}
