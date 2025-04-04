/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.wrapper;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "3.2.0";
    private static final boolean DEFAULT_VERIFY = true;
    private static final boolean DEFAULT_INTERACTIVE = false;

    private final boolean verify;
    private final boolean interactive;
    private final String url;
    private final String wrapperUrl;
    private final String wrapperJarPath;
    private final Properties systemProperties;
    private final CommandExecutor cmdExecutor;

    public static void main(String args[]) {
        if (args.length != 2) {
            System.err.println(" - ERROR wrapperUrl command-line argument");
            System.err.println(" - ERROR MAVEN_PROJECTBASEDIR command-line argument");
            System.exit(1);
        }

        File baseDir = new File(args[1]);
        String wrapperUrl = args[0];
        boolean verify = DEFAULT_VERIFY;
        boolean interactive = DEFAULT_INTERACTIVE;

        for (int i = 2; i < args.length; i++) {
            if (args[i].equals("--verify")) {
                verify = true;
            } else if (args[i].equals("--no-verify")) {
                verify = false;
            } else if (args[i].equals("--interactive")) {
                interactive = true;
            } else if (args[i].equals("--no-interactive")) {
                interactive = false;
            }
        }

        try {
            MavenWrapperDownloader downloader = new MavenWrapperDownloader(verify, interactive, wrapperUrl, baseDir);
            downloader.download();
        } catch (Exception e) {
            System.err.println("- Error downloading: " + e.getMessage());
            System.exit(1);
        }
    }

    private MavenWrapperDownloader(boolean verify, boolean interactive, String wrapperUrl, File baseDir) {
        this.verify = verify;
        this.interactive = interactive;
        this.url = wrapperUrl;
        this.wrapperUrl = wrapperUrl;
        this.wrapperJarPath = baseDir + "/.mvn/wrapper/maven-wrapper.jar";
        this.systemProperties = new Properties();
        this.cmdExecutor = new CommandExecutor();
    }

    private void download() throws Exception {
        File wrapperJar = new File(wrapperJarPath);
        if (wrapperJar.exists()) {
            System.out.println("- Download has already been completed.");
            return;
        }

        System.out.println("- Downloading: " + url);

        URL downloadUrl = new URL(url);
        URLConnection downloadConnection = downloadUrl.openConnection();
        if (downloadConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) downloadConnection;
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(" - Error downloading, response code: " + responseCode + " - " + httpURLConnection.getResponseMessage());
            }
        }

        try (InputStream inStream = downloadConnection.getInputStream();
             OutputStream outStream = new FileOutputStream(wrapperJar)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }

        if (verify) {
            verifyDownload();
        }

        System.out.println("- Download completed.");
    }

    private void verifyDownload() throws Exception {
        System.out.println("- Verifying download...");
        String checksumUrl = url + ".sha256";
        String checksum = downloadSha256(checksumUrl);
        String calculatedChecksum = calculateSha256(new File(wrapperJarPath));
        if (!calculatedChecksum.equals(checksum)) {
            throw new RuntimeException(" - Error verifying download, checksum: " + calculatedChecksum + ", expected: " + checksum);
        }
        System.out.println("- Checksum verified.");
    }

    private String downloadSha256(String sha256Url) throws Exception {
        URL url = new URL(sha256Url);
        URLConnection connection = url.openConnection();
        if (connection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(" - Error downloading sha256, response code: " + responseCode + " - " + httpURLConnection.getResponseMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.readLine();
        }
    }

    private String calculateSha256(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             DigestInputStream dis = new DigestInputStream(bis, MessageDigest.getInstance("SHA-256"))) {
            while (dis.read() != -1) {
                // Read the file
            }
            byte[] digest = dis.getMessageDigest().digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
    }

    private static class CommandExecutor {
        public int executeCommand(String command, boolean interactive) throws Exception {
            Process process = Runtime.getRuntime().exec(command);
            if (interactive) {
                process.waitFor();
            }
            return process.exitValue();
        }
    }
} 