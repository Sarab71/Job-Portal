// app/jobs/[id]/page.tsx
'use client';
import React, { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import axios from 'axios';

interface JobDto {
  id: number;
  title: string;
  description: string;
  skills: string;
  postedBy: string;
}

const JobDetailsPage = () => {
  const { id } = useParams();
  const [job, setJob] = useState<JobDto | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    const fetchJob = async () => {
      try {
        const res = await axios.get<JobDto>(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/jobs/${id}`);
        setJob(res.data);
      } catch (err) {
        console.error('Error fetching job:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchJob();
  }, [id]);

  if (loading) return <p>Loading...</p>;
  if (!job) return <p>Job not found.</p>;

  return (
    <div className="p-4 max-w-2xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-2xl font-bold mb-2">{job.title}</h1>
      <p className="text-gray-700 mb-4">{job.description}</p>
      <p><strong>Skills Required:</strong> {job.skills}</p>
      <p><strong>Posted By:</strong> {job.postedBy}</p>
    </div>
  );
};

export default JobDetailsPage;
