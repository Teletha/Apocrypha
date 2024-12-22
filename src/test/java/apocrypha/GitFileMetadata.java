/*
 * Copyright (C) 2024 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package apocrypha;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

public class GitFileMetadata {

    public static void main(String[] args) throws Exception {
        // Gitリポジトリのルートディレクトリを指定
        File repoDir = new File("");
        String filePath = "src/test/resources/article/JavaでPowerAssert.md"; // リポジトリ内のファイルパス

        try (Repository repository = Git.open(repoDir).getRepository()) {
            Git git = new Git(repository);

            System.out.println("=== 最終コミット情報 ===");
            printCommitInfo(git, repository, filePath, false);

            System.out.println("\n=== 初回コミット情報 ===");
            printCommitInfo(git, repository, filePath, true);
        }
    }

    private static void printCommitInfo(Git git, Repository repository, String filePath, boolean isFirst) throws Exception {
        Iterable<RevCommit> commits = git.log()
                .addPath(filePath) // 特定のファイルに限定
                .call();

        RevCommit targetCommit = null;
        if (isFirst) {
            for (RevCommit commit : commits) {
                targetCommit = commit; // 初回コミットが最後の要素
            }
        } else {
            targetCommit = commits.iterator().next(); // 最初のコミットが最後の要素
        }

        if (targetCommit != null) {
            System.out.println("コミット日時: " + targetCommit.getAuthorIdent().getWhen());
            System.out.println("コミット者: " + targetCommit.getAuthorIdent().getName());
            System.out.println("メール: " + targetCommit.getAuthorIdent().getEmailAddress());
        } else {
            System.out.println("指定されたファイルにはコミットがありません。");
        }
    }
}
