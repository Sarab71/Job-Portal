"use client"
import axios from 'axios';
import React, { useState } from 'react'
import { toast } from 'react-toastify';

const page = () => {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    skills: ''
  });
  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const url = `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/jobs`;
    const token = localStorage.getItem('token'); // ✅ ensure user is logged in

    const payload = {
      title: formData.title,
      description: formData.description,
      skills: formData.skills,
    };

    try {
      const res = await axios.post(url, payload, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success('Job posted successfully!');
      setFormData({ title: '', description: '', skills: '' }); // Optional: clear form
    } catch (err: any) {
      toast.error(err.response?.data?.message || 'Something went wrong.');
      console.error(err);
    }
  };

  return (
    <div className='mt-12 flex items-center justify-center bg-gray-100'>
      <div className='bg-white p-8 rounded-lg shadow-md w-full max-w-md'>
        <h2 className='text-2xl font-bold mb-6 text-center'>Post a Job</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Job Title
            </label>
            <input
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Job title"
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Job Description
            </label>
            <input
              type="text"
              name="description"
              value={formData.description}
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Job description"
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Skills
            </label>
            <input
              type="text"
              name="skills"
              value={formData.skills}
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Skills (comma separated)"
              required
            />
          </div>
          <button type='submit' className=' mt-5 w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition'>Post</button>
        </form>
      </div>
    </div>
  )
}

export default page