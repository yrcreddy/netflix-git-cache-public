package netflix.utils;

import java.util.Date;
import java.util.List;
import netflix.model.RepoView;

public class RepoViewUtil {
    public static RepoView convertToRepoView(String repo, Integer value) {
        String path = Constants.NETFLIX + "/" + repo;
        return RepoView.builder().path(path)
                .value(value)
                .build();
    }

    public static RepoView convertToRepoView(String path, Date date) {
        return RepoView.builder().path(path)
                .value(date.toInstant().toString())
                .build();
    }

    public static String[][] getStringArray(List<RepoView> repoViewResult) {
        String[][] result = new String[repoViewResult.size()][2];
        for(int i = 0; i < repoViewResult.size(); i++) {
            result[i][0] = repoViewResult.get(i).getPath();
            result[i][1] = repoViewResult.get(i).getValue().toString();
        }
        return result;
    }
}
