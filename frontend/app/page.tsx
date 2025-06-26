"use client";
import axios from 'axios';
import React, { useEffect, useState } from 'react';

type Job = {
  id: number;
  title: string;
  description: string;
  skills: string;
  postedAt: string;
  postedBy: string; // 👈 this is a string (name), based on your JobDto
};

const Home = () => {
  const [jobs, setJobs] = useState<Job[]>([]);

  useEffect(() => {
    axios.get(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/jobs/all`)
      .then((res) => {
        setJobs(res.data);
      })
      .catch((err) => {
        console.error("Error fetching jobs:", err);
      });
  }, []);

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-6">Available Jobs</h1>

      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
        {jobs.map((job) => (
          <div key={job.id} className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
            <h2 className="text-xl font-semibold text-gray-800">{job.title}</h2>
            <p className="text-sm mt-2 text-gray-700">{job.description.slice(0, 100)}...</p>
            <p className="mt-2 font-medium text-green-700">Skills: {job.skills}</p>
            <p className="text-sm text-gray-600 mt-2">Posted by: {job.postedBy}</p>
            <p className="text-sm text-gray-500">
              {new Date(job.postedAt).toLocaleDateString()}
            </p>
            <button className="mt-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 cursor-pointer">
              View Details
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
