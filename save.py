import csv

def save_to_file(jobs):
  file = open("jobs.csv", mode="w") 
  writer = csv.writer(file)
  writer.writerow(["name", "ingredient", "how", "img"])
  for job in jobs:
    writer.writerow(job.values())

  return